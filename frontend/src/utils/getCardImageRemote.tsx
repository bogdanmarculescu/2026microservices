const FILE_STORAGE_URL = import.meta.env.VITE_APP_FILE_STORAGE_URL ?? "http://localhost:8100";

export function getCardImageRemote(filename: string) : string {
    return `${FILE_STORAGE_URL}/api/files/${filename}`;
}


