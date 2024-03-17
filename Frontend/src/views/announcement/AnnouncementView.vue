<template>
    <main class="flex flex-col items-center justify-center">
        <div
            class="mt-5 font-fig flex items-center justify-center p-3 w-3/4 sm:w-2/4 h-4/5 text-2xl font-bold text-gray-900">
            Recipe(s)
        </div>

    
        <AnnouncementCard v-for="announcement in announcements" :key="announcement.recipeId" :announcement="announcement"></AnnouncementCard>

    </main>
</template>
  
<script setup lang="ts">
import { type AnnouncementItem } from '@/type'
import { computed, ref, watchEffect, type Ref, onMounted } from 'vue';
import { useAnnouncementStore } from '@/stores/announcement'
import AnnouncementCard from '@/components/AnnouncementCard.vue';

const announcements = ref<AnnouncementItem[] | null> (null)

onMounted(async () => {
    try {
        const response = await useAnnouncementStore().getAnnouncement
        announcements.value = response
    } catch (error) {
        console.log('Error fetching student data:', error)
    }
})

</script>
  