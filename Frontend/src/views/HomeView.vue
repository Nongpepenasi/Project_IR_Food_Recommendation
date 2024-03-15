<script setup lang="ts">
import { type AnnouncementItem } from '@/type'
import NProgress from 'nprogress'
import { computed, ref, watchEffect, type Ref, onMounted } from 'vue';
import { useAnnouncementStore } from '@/stores/announcement'
import AnnouncementCard from '@/components/AnnouncementCard.vue';

const announcements = ref<AnnouncementItem[] | null> (null)


onMounted(async () => {
    try {
        const response = await useAnnouncementStore().getAnnouncement
        announcements.value = response
        console.log('Yes')
    } catch (error) {
        console.log('Error fetching student data:', error)
    }
})

</script>

<template>
  <main class="flex flex-col items-center justify-center my-5 text-black">
    <h1 class="font-fig text-3xl font-bold  px-4 py-2 text-center">SE331 TERM PROJECT</h1>    <p class="font-mono text-center">Component-Based Software Development</p>
    <br>
    <p class="font-fig font-bold text-base ">Group Name: E-Mam_BodyMom</p>
    <h2 class="font-fig my-2 font-bold">Team Members</h2>
    <!-- <div class="grid lg:grid-cols-3 md:grid-cols-1 gap-24 gap-y-12 mt-10 "> -->
      <AnnouncementCard v-for="announcement in announcements" :key="announcement.recipeId" :announcement="announcement"></AnnouncementCard>
    <!-- </div> -->
  </main>
</template>
