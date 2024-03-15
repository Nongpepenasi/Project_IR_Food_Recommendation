import apiClient from "./AxiosClient";
import type { AxiosResponse } from "axios";
import type { AnnouncementItem } from "@/type";

export default {
    getAnnouncements() : Promise<AxiosResponse<AnnouncementItem[]>> {
        return apiClient.get<AnnouncementItem[]>("/announcements")
    },
    getAnnouncementsByPage(perPage: number, page: number) : Promise<AxiosResponse<AnnouncementItem[]>> {
        return apiClient.get<AnnouncementItem[]>("/announcement?_limit=" + perPage + '&_page=' + page)
    }
}