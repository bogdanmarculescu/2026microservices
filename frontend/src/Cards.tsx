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

    const [playedCard, setPlayedCard] = useState(null);
    const [bidCard, setBidCard] = useState(null);

    useEffect(() => {
        const fetchData = async () => {
            try {
                const response = await fetch('http://localhost:8000/api/mono');
                if (!response.ok) {
                    throw new Error(`Failed to fetch: ${response.status}`);
                }
                const result = await response.json();
                setRound(result);
                setTopic(result.topic);
                setRoundId(result.id);
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
    const topicCardImage = getCardImage(round.topic.filename);

    if (!topicCardImage) {
        // @ts-ignore
        return (
            <>
                <div>
                    <p>Card image not found: {topic.filename}</p>
                </div>
            </>

        );
    }



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
            if (playedCard) setPlayerHand((prev) => [...prev, playedCard]);
            setPlayedCard(card);
        } else if (type === "bid") {
            if (bidCard) setPlayerHand((prev) => [...prev, bidCard]);
            setBidCard(card);
        }

        // remove from hand (so it can't be reused)
        setPlayerHand((prev) => prev.filter((c) => c.id !== card.id));
    };

    const allowDrop = (e) => e.preventDefault();

    // Submit selection
    const handleSubmit = () => {
        if (!playedCard || !bidCard) {
            alert("Please select both a Played card and a Bid card!");
            return;
        }

        fetch("http://localhost:8000/api/mono/submitMove", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ roundId, playedCard, bidCard }),
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

                            const cardSrc = getCardImage(card.filename);
                            if (!cardSrc) {
                                // @ts-ignore
                                return (
                                    <p key={card.id} style={{color: "red"}}>
                                        Not found: {card.filename}
                                    </p>
                                );
                            }
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
                        label="PlayedCard"
                        card={playedCard}
                        onDrop={(e) => handleDrop(e, "played")}
                    />
                    {/* Bid Card Area */}
                    <DropZone
                        label="Bid Card"
                        card={bidCard}
                        onDrop={(e) => handleDrop(e, "bid")}
                    />
                </div>
            </div>
        );
}

export default Cards
