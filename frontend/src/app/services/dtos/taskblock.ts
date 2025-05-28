export interface TaskBlockResponseDTO {
    id: Number;
    title: String,
    userId: Number;
}


export interface HasBlockActiveResponseDTO{
    blockActive: boolean
}



// Para la lista de bloques de tarea
export interface TaskBlockAllResponseDTO {
    id: number;
    title: string;
    createdAt: string;
}


export interface ListTaskAllResponseDTO {
    listTaskBlock: TaskBlockAllResponseDTO[];
}
