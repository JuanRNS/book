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

export interface IResponseContacts{
    content: IResponseContact[];
    pageable: IPageable;
    size: number;
    totalElements: number;
    totalPages: number;
}

interface IPageable{
    pageNumber: number;
    pageSize: number;
}