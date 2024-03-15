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
    recipeId: number;
    name: string;
    authorId: number;
    authorName: string;
    cookTime: string;
    prepTime: string;
    totalTime: string;
    datePublished: string;
    description: string[];
    images: string[];
    recipeCategory: string;
    keywords: string[];
    recipeIngredientQuantities: string[];
    recipeIngredientParts: string[];
    aggregatedRating: number;
    reviewCount: number;
    calories: number;
    fatContent: number;
    saturatedFatContent: number;
    cholesterolContent: number;
    sodiumContent: number;
    carbohydrateContent: number;
    fiberContent: number;
    sugarContent: number;
    proteinContent: number;
    recipeServings: string;
    recipeYield: string;
    recipeInstructions: string[];
}