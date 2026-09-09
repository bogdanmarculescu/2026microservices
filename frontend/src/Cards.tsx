import {useEffect, useState} from 'react'
import './App.css'
import {getCardImage} from "./utils/getCardImage.tsx";
import DropZone from "./components/DropZone";
import { Card } from "./model/Card.tsx";
import { Round } from "./model/Round.tsx";

function Cards() {
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    //const [round, setRound] = useState<Round | null>(null);
    const [topic, setTopic] = useState<Card | null>(null);
    const [roundId, setRoundId] = useState<number | null>(null);
    const [playerHand, setPlayerHand] = useState<Card[]>([]);

    const [playerCard, setPlayerCard] = useState<Card | null>(null);
    const [playerBid, setPlayerBid] = useState<Card | null>(null);

    useEffect(() => {
        const fetchData = async () => {
            try {
                const response = await fetch('http://localhost:8000/api/round');
                if (!response.ok) {
                    throw new Error(`Failed to fetch: ${response.status}`);
                }
                const result : Round = await response.json();
                console.log(result);
                //setRound(result);
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
    if (!topic) {
        // @ts-ignore
        return (
            <>
                <div>
                    <p> Topic card not found </p>
                </div>
            </>

        );
    }
    const topicCardImage = getCardImage(topic.filename);

    if (error) {
        // @ts-ignore
        return <div>Error: {error.message}</div>;
    }

    // Handle dragging
    const handleDragStart = (e: React.DragEvent, card : Card) => {
        e.dataTransfer.setData("cardId", String(card.id));
    };

    // Handle drop into a target area
    const handleDrop = (e : React.DragEvent,  type : string) => {
        e.preventDefault();
        const cardId = e.dataTransfer.getData("cardId");
        const card = playerHand.find((c : Card) => c.id === parseInt(cardId));

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

    //const allowDrop = (e: React.DragEvent) => e.preventDefault();

    // Submit selection
    const handleSubmit = () => {
        if (!playerCard || !playerBid) {
            alert("Please select both a Played card and a Bid card!");
            return;
        }

        fetch("http://localhost:8000/api/round/submitMove", {
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
                         alt={`${topic.cardValue} of ${topic.suite}`}
                         style={{ width: "100px" }}
                    />
                </div>

                {/* Player Hand */}
                <div>
                    <h1>
                        Player hand:
                    </h1>
                    <div style={{ display: 'flex', gap: '10px' }}>
                        { playerHand.map((card : Card)  => {
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
                                         alt={`${card.cardValue} of ${card.suite}`}
                                         draggable={true}
                                         onDragStart={(e) => handleDragStart(e, card)}
                                         style={{ width: "100px", cursor:"grab" }}
                                    />
                                    <div>
                                        {card.cardValue} of {card.suite}
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
                        onDrop={(e : React.DragEvent) => handleDrop(e, "played")}
                    />
                    {/* Bid Card Area */}
                    <DropZone
                        label="Bid Card"
                        card={playerBid}
                        onDrop={(e : React.DragEvent) => handleDrop(e, "bid")}
                    />
                </div>
            </div>
        );
}

export default Cards
