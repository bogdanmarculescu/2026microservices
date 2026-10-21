
1. Calling the endpoint without a token returns `401 Unauthorized`.
2. Obtaining an access token from Keycloak.
3. Calling the same endpoint with that token succeeds.

The important detail is to use the same issuer URL inside Keycloak's token and Spring Security's configuration. In Docker, that can be `http://keycloak:8080/realms/cards` if Keycloak is configured to publish that hostname.

## 1. Use a temporary client container

You can do this without modifying your `docker-compose.yml`. Assuming your Docker network is named `m2026`, run a temporary Alpine container that installs `curl` and `jq`:

```
docker run --rm -it \
  --network m2026 \
  alpine:3.22 sh
```

Inside that shell, install the tools:

```
apk add --no-cache curl jq
```

This container can communicate with `keycloak:8080` and `gateway:8100` by their Docker service names, provided they share the network.

## 2. Demonstrate the endpoint without authentication

From inside the temporary container:

```
curl -i http://gateway:8100/api/deck/secured/cards
```

Expected result:

```
HTTP/1.1 401 Unauthorized
```

Then compare it with the public endpoint:

```
curl -i http://gateway:8100/api/deck/cards
```

The public endpoint should still work without a token.


## 3. Obtain a token from Keycloak

For a simple classroom demonstration, use a dedicated test user and a temporary Keycloak client with Direct Access Grants enabled. This password-based grant is suitable only for an isolated legacy-style demo; it is not recommended for production applications.

Inside the same container:

```
TOKEN_RESPONSE=$(curl -s -X POST \
  http://keycloak:8080/realms/Mikrotjenester/protocol/openid-connect/token \
  -H "Content-Type: application/x-www-form-urlencoded" \
  -d "client_id=deck-client" \
  -d "grant_type=password" \
  -d "username=atester" \
  -d "password=tester")

TOKEN=$(echo "$TOKEN_RESPONSE" | jq -r '.access_token')

test "$TOKEN" != "null" && test -n "$TOKEN" \
  && echo "Access token obtained" \
  || echo "$TOKEN_RESPONSE"
```

Replace the test credentials with the ones you configured in Keycloak. If your client requires a client secret, supply it as well.

## 4. Call the secured endpoint with the token

```
curl -i \
  -H "Authorization: Bearer $TOKEN" \
  http://gateway:8100/api/deck/secured/cards
```

```
curl -i \
  -H "Authorization: Bearer $TOKEN" \
  http://deck:8001/api/deck/secured/cards
```

Expected result: `200 OK`, assuming the Gateway routes correctly, the token is valid, and the secured endpoint returns a successful response.

You can now demonstrate the whole authentication flow without leaving the terminal.

## 5. Make the Docker configuration consistent

For this to work, your Spring Security resource server must use the same issuer that Keycloak publishes:

```
spring.security.oauth2.resourceserver.jwt.issuer-uri=http://keycloak:8080/realms/cards
```

And Keycloak must publish this exact issuer URL. Verify it from the temporary container:

```
curl -s \
  http://keycloak:8080/realms/cards/.well-known/openid-configuration \
  | jq -r '.issuer'
```

Expected output:

```
http://keycloak:8080/realms/cards
```

If the output differs, fix Keycloak's hostname configuration before debugging Spring Security further.

One final note: if the Gateway itself is not secured and only Deck enforces authentication, the request may reach Deck before receiving the `401`. That is fine for this lab and provides another opportunity to teach where security is actually enforced.