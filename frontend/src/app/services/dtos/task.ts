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

// Para crear una tarea nueva
export interface TaskCreateRequestDTO{
    title: string,
    userId: number
}

export interface TaskCreateResponseDTO{
    id: number,
    title: string
}