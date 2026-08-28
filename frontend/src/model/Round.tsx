import type {Card} from "./Card.tsx";

export class Round{
    topic : Card | null;
    roundId : number | null;
    playerCards : Card[];

    constructor(roundId: number, topic : Card, playerCards : Card[]){
        this.roundId = roundId;
        this.topic = topic;
        this.playerCards = playerCards;
    }
}