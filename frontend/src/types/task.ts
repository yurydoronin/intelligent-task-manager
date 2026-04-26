export interface Task {
    id: string
    name: string
    description?: string
    priority: string
    status: string
    createdAt: string
    deadline?: string
    completedAt?: string
}