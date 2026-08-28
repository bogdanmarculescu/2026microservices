import {useEffect, useState} from 'react'
import './App.css'
import {getCardImage} from "./utils/getCardImage.tsx";
import DropZone from "./components/DropZone";

function Cards() {
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    const [round, setRound] = useState(null);
    const [topic, setTopic] = useState(null);
    const [roundId, setRoundId] = useState(null);
    const [playerHand, setPlayerHand] = useState([]);

    const [playerCard, setPlayerCard] = useState(null);
    const [playerBid, setPlayerBid] = useState(null);

    useEffect(() => {
        const fetchData = async () => {
            try {
                const response = await fetch('http://localhost:8000/api/mono/round');
                if (!response.ok) {
                    throw new Error(`Failed to fetch: ${response.status}`);
                }
                const result = await response.json();
                console.log(result);
                setRound(result);
                setTopic(result.topic);
                setRoundId(result.roundId);
                setPlayerHand(Object.values(result.playerCards));
            } catch (err){
                // @ts-ignore
                setError(err);
            } finally {
                setLoading(false);
            }
        };

        fetchData();
    }, []); // Empty dependency array ensures this runs once on mount

    if (loading) {
        return <div>Loading...</div>;
    }

    if (error) {
        // @ts-ignore
        return <div>Error: {error.message}</div>;
    }

    // @ts-ignore
    if (!round.topic) {
        // @ts-ignore
        return (
            <>
                <div>
                    <p>Card image not found: {topic}</p>
                </div>
            </>

        );
    }
    const topicCardImage = getCardImage(round.topic.filename);





    if (error) {
        // @ts-ignore
        return <div>Error: {error.message}</div>;
    }

    // Handle dragging
    const handleDragStart = (e, card) => {
        e.dataTransfer.setData("cardId", card.id);
    };

    // Handle drop into a target area
    const handleDrop = (e, type) => {
        e.preventDefault();
        const cardId = e.dataTransfer.getData("cardId");
        const card = playerHand.find((c) => c.id === parseInt(cardId));

        if (!card) return;

        if (type === "played") {
            if (playerCard) setPlayerHand((prev) => [...prev, playerCard]);
            setPlayerCard(card);
        } else if (type === "bid") {
            if (playerBid) setPlayerHand((prev) => [...prev, playerBid]);
            setPlayerBid(card);
        }

        // remove from hand (so it can't be reused)
        setPlayerHand((prev) => prev.filter((c) => c.id !== card.id));
    };

    const allowDrop = (e) => e.preventDefault();

    // Submit selection
    const handleSubmit = () => {
        if (!playerCard || !playerBid) {
            alert("Please select both a Played card and a Bid card!");
            return;
        }

        fetch("http://localhost:8000/api/mono/submitMove", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ roundId, topic, playerCard, playerBid }),
        })
            .then((res) => {
                if (res.ok) {
                    alert("Move submitted!");
                } else {
                    alert("Error submitting move.");
                }
            })
            .catch(() => alert("Network error submitting move."));
    };

    // @ts-ignore
    return (
            <div style={{ padding: "20px" }}>
                <button
                    onClick={handleSubmit}
                    style={{
                        marginTop: "40px",
                        padding: "10px 20px",
                        fontSize: "16px",
                        cursor: "pointer",
                    }}
                >
                    Submit Move
                </button>
                <div >
                    <h1> Topic Card: </h1>
                    <img src={topicCardImage}
                         alt={topic}
                         style={{ width: "100px" }}
                    />
                </div>

                {/* Player Hand */}
                <div>
                    <h1>
                        Player hand:
                    </h1>
                    <div style={{ display: 'flex', gap: '10px' }}>
                        { playerHand.map((card)  => {
                            if (!card) {
                                // @ts-ignore
                                return (
                                    <p key={card} style={{color: "red"}}>
                                        Not found: {card}
                                    </p>
                                );
                            }
                            const cardSrc = getCardImage(card.filename);


                            return (
                                <div key={card.id} style={{ textAlign: 'center' }}>
                                    <img key={card.id}
                                         src={cardSrc}
                                         alt={`${card.value} of ${card.suite}`}
                                         draggable={true}
                                         onDragStart={(e) => handleDragStart(e, card)}
                                         style={{ width: "100px", cursor:"grab" }}
                                    />
                                    <div>
                                        {card.value} of {card.suite}
                                    </div>
                                </div>

                            );
                        })

                        }
                    </div>
                </div>

                <div style={{ marginTop: "40px", display: "flex", gap: "40px" }}>

                    {/* Played Card Area */}
                    <DropZone
                        label="playerCard"
                        card={playerCard}
                        onDrop={(e) => handleDrop(e, "played")}
                    />
                    {/* Bid Card Area */}
                    <DropZone
                        label="Bid Card"
                        card={playerBid}
                        onDrop={(e) => handleDrop(e, "bid")}
                    />
                </div>
            </div>
        );
}

export default Cards
