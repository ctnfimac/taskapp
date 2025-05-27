export interface TaskResponseDTO {
    id: number;
    title: string;
    done: boolean;
}


export interface TaskAllResponseDTO {
    idTaskBlock: number,
    titleBlock: string;
    listTasks: TaskResponseDTO[];
}


// Para el cambio de estado de la tarea
export interface TaskRequestToogleDTO{
    taskBlockId: number,
    userId: number
}

export interface TaskResponseToogleDTO{
    title: string,
    done: boolean, 
    taskId: number 
}