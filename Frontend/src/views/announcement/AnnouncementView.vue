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
import NProgress from 'nprogress'
import { computed, ref, watchEffect, type Ref, onMounted } from 'vue';
import { useAnnouncementStore } from '@/stores/announcement'
import FilePreview from '../../components/FilePreview.vue';
import { useTeacherStore } from '../../stores/teacher'
import { type TeacherItem } from '@/type'
import AnnouncementCard from '@/components/AnnouncementCard.vue';

const announcements = ref<AnnouncementItem[] | null> (null)
const teacher = ref<TeacherItem | null>(null)

const currentTime = ref('');
const currentDate = ref('');

// Function to update the currentTime and currentDate
const updateDateTime = () => {
  const now = new Date();
  const hours = now.getHours();
  const minutes = now.getMinutes();
  const formattedTime = `${hours}:${minutes < 10 ? '0' : ''}${minutes}`;
  currentTime.value = formattedTime;

  const options = { year: 'numeric', month: 'long', day: 'numeric' };
  currentDate.value = now.toLocaleDateString(undefined, options);
};
// let files

onMounted(async () => {
    try {
        const response = await useAnnouncementStore().getAnnouncement
        announcements.value = response
        const response2 = await useTeacherStore().getTeacher()
        teacher.value = response2
        updateDateTime();
        setInterval(updateDateTime, 60000);
    } catch (error) {
        console.log('Error fetching student data:', error)
    }
})

</script>
  