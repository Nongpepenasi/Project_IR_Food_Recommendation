export interface StudentItem {
    name: string;
    surname: string;
    id: string;
    images: string[];
    courselist: string[];
    teacher: string[];
    comment?: any[]
    roles: string []
    department: string
}
export interface TeacherItem {
    [x: string]: any;
    name: string;
    surname: string;
    images: string[];
    id: string;
    roles: string []
    ownStudent: string []
}
export interface CommentInfo{
    id: string;
    comment: string;
}
export interface AnnouncementItem {
    id: number
    recipeId: number;
    name: string;
    description: string;
    images: string;
    keywords: string;
    recipeIngredientQuantities: string;
    recipeIngredientParts: string;
    recipeInstructions: string;
}
export interface FolderItem{
    id: number;
    name: string;
    ownAnnouncement: string []
}