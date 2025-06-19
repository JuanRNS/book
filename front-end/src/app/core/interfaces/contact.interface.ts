export interface IRequestContact{
    name: string;
    email: string;
    phone: string;
    address?: string;
    favorite?: boolean;
    description?: string;
}

export interface IResponseContact{
    id: number;
    name: string;
    email: string;
    phone: string;
    address: string;
    favorite: boolean;
    description: string;
    createdAt: string;
    updatedAt: string;
}