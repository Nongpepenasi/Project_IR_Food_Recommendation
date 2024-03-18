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
import { faL } from '@fortawesome/free-solid-svg-icons';

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
let keywords: string[] = [];async function searchRecipe () {
  await useAnnouncementStore().getAnnouncementsBySearch(keyword.value);
  router.push({name: 'recipes-view', params: {id: 0}});
  // console.log(useAnnouncementStore().getAnnouncement.length)
}

let suggestedCorrections: String[] = [];
// let check = true;

async function spellCheck() {
  if (keyword.value.length == 0){
    suggestedCorrections = []; 
    // check = true
  }
  if (keyword.value.includes(' ') && keywords.values.length == 0){
    keywords = keyword.value.split(' ').filter(word => word.trim() !== '');
  
  try {
    console.log(keywords)
        const responses = await Promise.all(keywords.map(word => useAnnouncementStore().getSpell(word)));
        suggestedCorrections = responses.filter(response => response.data.length > 1).map(response => response.data);        
        console.log(suggestedCorrections)
        // const response = await useAnnouncementStore().getSpell(keyword.value)
        // suggestedCorrections = response.data
      }
      catch (error) {
        console.error('Error while spell checking:', error);
      }
  }
}

function replaceWord(suggestion: string) {
      keyword.value = suggestion;
      suggestedCorrections = []; 
      // check = false
    }

</script>

<template>
  <main class="flex flex-col items-center justify-center my-5 text-black">
    <h1 class="font-fig text-3xl font-bold  px-4 py-2 text-center">SE323 TERM PROJECT</h1>    <p class="font-mono text-center">Intro to Infomation Retriev</p>
    <br>
    <div v-if="authStore.userRole == 'ROLE_USER'" class="flex justify-center w-full p-3 sm:w-2/4">
             <input
              v-model="keyword"
              type="text"
              placeholder="Search..."
              class="w-full h-10 border rounded-md text-gray-900 relative"
              @input="spellCheck"/>
              <div v-if="suggestedCorrections.length > 0 && keywords.length == 1" class="absolute mt-1 w-1/3 rounded-md bg-white shadow-lg">
                <ul class="py-1">
                  <li v-for="(suggestions, index) in suggestedCorrections" :key="index">
                    <template v-if="suggestions.length > 1">
                      <li v-for="(suggestion, subIndex) in suggestions" :key="subIndex" @click="replaceWord(suggestion)">
                        <a href="#" class="block px-4 py-2 text-sm text-gray-700 hover:bg-gray-100">{{ suggestion }}</a>
                      </li>
                    </template>
                  </li>
                </ul>
              </div>
              <!-- <div v-if="suggestedCorrections.length > 0 && keyword.length > 0" class="absolute mt-1 w-1/3 rounded-md bg-white shadow-lg">
                <ul class="py-1">
                  <li v-for="(suggestion, index) in suggestedCorrections" :key="index" @click="replaceWord(suggestion)">
                    <a href="#" class="block px-4 py-2 text-sm text-gray-700 hover:bg-gray-100">{{ suggestion }}</a>
                 </li>
               </ul>
             </div> -->
              <button @click="searchRecipe" class="px-4 py-2 bg-blue-500 text-white rounded-md">Search</button>
              <!-- <RouterLink to="/recipes">
                
              </RouterLink> -->
              
    </div>
    <div class="grid lg:grid-cols-3 md:grid-cols-1 gap-24 gap-y-12 mt-10 ">
      <!-- <p>{{ announcements }}</p> -->
      <AnnouncementCard v-for="announcement in announcements" :key="announcement.recipeId" :announcement="announcement"></AnnouncementCard>

      
    </div>
  </main>
</template>
