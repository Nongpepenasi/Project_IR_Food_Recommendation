<script setup lang="ts">
import { type AnnouncementItem } from '@/type'
import { computed, ref, watchEffect, type Ref, onMounted } from 'vue';
import { useAnnouncementStore } from '@/stores/announcement'
import AnnouncementCard from '@/components/AnnouncementCard.vue';

const announcements = ref<AnnouncementItem[] | null> (null)


onMounted(async () => {
    try {
        // console.log((useAnnouncementStore().getAnnouncement))
        const response = await useAnnouncementStore().getAnnouncement
        // console.log('yes')
        // console.log('Recipes: '+useAnnouncementStore().getAnnouncement.length)
        if (response !== null && response !== undefined) {
            // const results = response.values
            announcements.value = response
            console.log(announcements)
        }
    } catch (error) {
        console.log('Error fetching student data:', error)
    }
})

</script>

<template>
    <main class="flex flex-col items-center justify-center">
        <div
            class="mt-5 font-fig flex items-center justify-center p-3 w-3/4 sm:w-2/4 h-4/5 text-2xl font-bold text-gray-900">
            Recipe(s)
            
        </div>

        <div v-if="announcements">
            <!-- <p>{{ announcements }}</p> -->
            <AnnouncementCard v-for="announcement in announcements" :key="announcement.recipeId" :announcement="announcement"></AnnouncementCard>
        </div>
        

    </main>
</template>
