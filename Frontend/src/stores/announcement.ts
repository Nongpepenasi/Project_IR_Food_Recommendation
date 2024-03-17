import AnnouncementService from "@/services/AnnouncementService";
import type { AnnouncementItem } from "@/type";
import axios from "axios";
import { defineStore } from "pinia";

export const useAnnouncementStore = defineStore('announcement', {
    state: () => ({
        announcements: [] as AnnouncementItem[]
    }),
    getters: {
        getAnnouncement: (state) => state.announcements,
        getAnnouncementById: (state) => async (id: Number) => {
            const response = state.announcements.find(announcement => announcement.recipeId == id)
            return new Promise<AnnouncementItem | null>((resolve) => {
                resolve(response || null)
            })
        }
    },
    actions: {
        setAnnouncements(announcements: AnnouncementItem[]) {
            this.announcements = announcements
        },
        async fetchAnnouncements() {
            const response = await AnnouncementService.getAnnouncementsByPage(3,1)
            this.setAnnouncements(response.data)
        },
        async getAnnouncementsBySearch(keyword: string) {
            const response = await AnnouncementService.getAnnouncementBySearch(keyword)
            this.setAnnouncements(response.data)
            AnnouncementService.postAnnouncements(response.data)
            // console.log(response.data)
            // console.log(this.getAnnouncement)
        }
    }
})