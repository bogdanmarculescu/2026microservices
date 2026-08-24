import { getCardImage } from "../utils/getCardImage";

// @ts-ignore
export default function DropZone({ label, card, onDrop }) {
    const allowDrop = (e) => e.preventDefault();

    return (
        <div
            onDrop={onDrop}
            onDragOver={allowDrop}
            style={{
                border: "2px dashed gray",
                borderRadius: "8px",
                width: "150px",
                height: "200px",
                display: "flex",
                justifyContent: "center",
                alignItems: "center",
            }}
        >
            {card ? (
                <img
                    src={getCardImage(card.filename)}
                    alt={`${card.value} of ${card.suite}`}
                    style={{ width: "100px" }}
                />
            ) : (
                <span>{label}</span>
            )}
        </div>
    );
}
