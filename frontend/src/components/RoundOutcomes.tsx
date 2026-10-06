import { useEffect, useState } from "react";

interface Topic {
    filename: string;
}

interface Round {
    roundId: number;
    topic: Topic;
    outcome: number;
}

export default function RoundOutcomes() {
    const [rounds, setRound] = useState<Round[]>([]);

    useEffect(() => {
        fetch("http://localhost:8100/api/resolver")
            .then(response => {
                if(!response.ok) {
                    throw new Error("Failed to fetch round data");
                }
                return response.json();
            })
            .then(data => {
                setRound(data);
            })
            .catch(error => console.error(error));
    }, []);

    return (
        <div>
            <h2>Rounds</h2>
            {rounds.map(round => (
                <div key={round.roundId} style={{ display: 'grid', gridTemplateColumns: '1fr 1fr 1fr', gap: '16px', justifyContent: 'space-between', justifyItems: 'left' }}>
                    <div> <strong>Round:</strong> {round.roundId} </div>
                    <div> <strong>Topic:</strong> {round.topic.filename} </div>
                    <div> <strong>Outcome:</strong> {round.outcome} <hr /> </div>
                </div> ))
            }
        </div> );
}