import apiClient from "./AxiosClient";
import type { AxiosResponse } from "axios";
import type { AnnouncementItem } from "@/type";

export default {
    getAnnouncements() : Promise<AxiosResponse<AnnouncementItem[]>> {
        return apiClient.get<AnnouncementItem[]>("/announcements")
    },
    getAnnouncementsByPage(perPage: number, page: number) : Promise<AxiosResponse<AnnouncementItem[]>> {
        return apiClient.get<AnnouncementItem[]>("/announcement?_limit=" + perPage + '&_page=' + page)
    },
    getAnnouncementBySearch(query: string) : Promise<AxiosResponse<AnnouncementItem[]>> {
        const apiURL = import.meta.env.VITE_API_URL;
        return apiClient.get<AnnouncementItem[]>(`${apiURL}/search_es?query=` + query);
        // return apiClient.get<AnnouncementItem[]>("/announcements")
    },
    postAnnouncements(data: AnnouncementItem[]) : Promise<AxiosResponse<AnnouncementItem[]>> {
        return apiClient.post<AnnouncementItem[]>("/announcements", data)
    },
}