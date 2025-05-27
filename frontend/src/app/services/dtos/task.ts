export interface TaskResponseDTO {
    id: number;
    title: string;
    done: boolean;
}


export interface TaskAllResponseDTO {
    titleBlock: string;
    listTasks: TaskResponseDTO[];
}