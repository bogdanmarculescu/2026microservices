export class Card {
    id: number;
    cardValue: number;
    suite: string;
    filename: string;

    constructor(
        id: number,
        cardValue: number,
        suite: string,
        filename: string,
    ) {
        this.id = id;
        this.cardValue = cardValue;
        this.suite = suite;
        this.filename = filename;
    }
}
