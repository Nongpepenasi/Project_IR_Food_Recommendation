import apiClient from "./AxiosClient";
import type { AxiosResponse } from "axios";
import type { FolderItem } from "@/type";

export default {
    getFolders() : Promise<AxiosResponse<FolderItem[]>> {
        return apiClient.get<FolderItem[]>("/folders")
    },
    getFolder(id: number) : Promise<AxiosResponse<FolderItem>> {
        return apiClient.get<FolderItem>("/folder/" + id)
    },
    addFolder(name: String) : Promise<AxiosResponse<FolderItem>> {
        return apiClient.post<FolderItem>("/addFolder", { name: name })
    },
    deleteFolder(id: number) : Promise<AxiosResponse<FolderItem>> {
        return apiClient.post<FolderItem>("/deleteFolder/" + id)
    },
    addRecipe(data:Object) : Promise<AxiosResponse<FolderItem>> {
        return apiClient.post<FolderItem>("/addRecipe", data)
    },
    deleteRecipe(data: Object) : Promise<AxiosResponse<FolderItem>> {
        return apiClient.post<FolderItem>("/deleteRecipe", data)
    },
}