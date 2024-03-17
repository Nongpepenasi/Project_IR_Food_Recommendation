<script setup lang="ts">
import { type AnnouncementItem } from '@/type'
import NProgress from 'nprogress'
import { computed, ref, watchEffect, type Ref, onMounted } from 'vue';
import { useAnnouncementStore } from '@/stores/announcement'
import AnnouncementCard from '@/components/AnnouncementCard.vue';
import apiClient from '@/services/AxiosClient';
import AnnouncementService from '@/services/AnnouncementService';
import { useRouter, RouterLink } from 'vue-router'
import { useAuthStore } from '@/stores/auth.ts'

const announcements = ref<AnnouncementItem[] | null> (null)
const router = useRouter()
const authStore = useAuthStore()
const id = localStorage.getItem('id')


onMounted(async () => {
    try {
        const response = await useAnnouncementStore().getAnnouncement
        announcements.value = response
        // console.log(announcements)
    } catch (error) {
        console.log('Error fetching student data:', error)
    }
})

const keyword = ref('')
async function searchRecipe () {
  await useAnnouncementStore().getAnnouncementsBySearch(keyword.value);
  router.push({name: 'recipes-view', params: {id: 0}});
  // console.log(useAnnouncementStore().getAnnouncement.length)
}

</script>

<template>
  <main class="flex flex-col items-center justify-center my-5 text-black">
    <h1 class="font-fig text-3xl font-bold  px-4 py-2 text-center">SE331 TERM PROJECT</h1>    <p class="font-mono text-center">Component-Based Software Development</p>
    <br>
    <p class="font-fig font-bold text-base ">Group Name: E-Mam_BodyMom</p>
    <h2 class="font-fig my-2 font-bold">Team Members</h2>
    <div class="flex justify-center w-full p-3 sm:w-2/4 ">
             <input
              v-model="keyword"
              type="text"
              placeholder="Search..."
              class="w-full h-10 border rounded-md text-gray-900"/>
              <button @click="searchRecipe" class="px-4 py-2 bg-blue-500 text-white rounded-md">Search</button>
              <!-- <RouterLink to="/recipes">
                
              </RouterLink> -->
              
    </div>
    <div class="grid lg:grid-cols-3 md:grid-cols-1 gap-24 gap-y-12 mt-10 ">
      <!-- <p>{{ announcements }}</p> -->
      <AnnouncementCard v-for="announcement in announcements" :key="announcement.recipeId" :announcement="announcement"></AnnouncementCard>
      <!-- <AnnouncementCard v-for="(announcement, index) in announcements" :key="announcement.recipeId" :announcement="announcement" v-if="index < 3"></AnnouncementCard> -->

      
    </div>
  </main>
</template>
