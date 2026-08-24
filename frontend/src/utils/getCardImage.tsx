// Import all SVGs from the folder once
const cardImages = import.meta.glob("../assets/cardImages/*.svg", { eager: true });

/**
 * Finds the matching card image for a given filename.
 * @param {string} fileName - e.g., "ace_of_spades.svg"
 * @returns {string|null} The URL of the SVG, or null if not found.
 */

export function getCardImage(fileName: string) {
    const match = Object.entries(cardImages)
        .find(([path]) =>
            path.endsWith(fileName)
        );
    // @ts-ignore
    return match ? match[1].default : null;
}