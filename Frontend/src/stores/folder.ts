import FolderService from "@/services/FolderService";
import type { FolderItem, AnnouncementItem } from "@/type";
import axios from "axios";
import { defineStore } from "pinia";

export const useFolderStore = defineStore('folder', {
    state: () => ({
        folders: [] as FolderItem[]
    }),
    getters: {
        getFolder: (state) => state.folders,
        getFolderById: (state) => async (id: Number) => {
            const response = state.folders.find(folder => folder.id == id)
            return new Promise<FolderItem | null>((resolve) => {
                resolve(response || null)
            })
        }
    },
    actions: {
        setFolders(folders: FolderItem[]) {
            this.folders = folders
        },
        async fetchFolders() {
            const response = await FolderService.getFolders()
            this.setFolders(response.data)
        },
        addFolder(data: String){
            FolderService.addFolder(data)
        },
        deleteFolder(id: number){
            FolderService.deleteFolder(id)
        },
        addRecipe(data: Object){
            FolderService.addRecipe(data)
        },
        deleteRecipe(data: Object){
            FolderService.deleteRecipe(data)
        },
    }
})