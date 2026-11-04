# 2026 - PG3402 - Microservices

This repository will contain the code associated with the course. 

The repository will be updated as the course progresses.

Cards from: https://code.google.com/archive/p/vector-playing-cards/

## General startup procedure.

To start the project:

```
docker compose up -d
```

The services _resolver_ and _deck_ are set up for scaling. To start those scaled:

```
docker compose up -d --scale resolver=2 --scale deck=2
```


# In addition

## Cheat sheet

An incomplete cheat sheet of relevant terminal commands: 
- [Cheat Sheet](cheat_sheet.md)
- [Startup Procedure](startup.md)