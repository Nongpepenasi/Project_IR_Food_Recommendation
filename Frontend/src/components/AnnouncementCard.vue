<script setup lang="ts">
import type { AnnouncementItem } from '@/type'
import type { PropType } from 'vue'
import { useAuthStore } from '@/stores/auth.ts'

const authStore = useAuthStore()

defineProps({
  announcement: {
    type: Object as PropType<AnnouncementItem>,
    require: true
  }
})
</script>

<template>
  <main class="flex flex-col items-center justify-center">
    <RouterLink :to="{ name: 'announcement-detail', params: { id: announcement?.recipeId } }">
      <div
        class="mt-5 mb-10 font-fig flex flex-col items-left justify-left p-3 w-3/4 sm:w-2/4 h-auto text-xl font-bold text-gray-900 bg-white border border-gray-300 rounded-lg shadow-md"
      >
        <div class="flex items-center space-x-3">
          <img
            :src="announcement?.images[0]"
            alt="Person's Photo"
            class="object-cover w-20 h-20 rounded-full"
          />
          <div>
            <p class="font-fig font-semibold text-md">{{ announcement?.authorName }}</p>
            <p class="font-fig text-sm font-semibold text-gray-600">
              Date: {{ announcement?.datePublished }}
            </p>
            <!-- <p class="font-fig text-sm font-semibold text-gray-600">Time: {{currentTime}}</p> -->
          </div>
        </div>
        <div
          class="flex flex-col items-center mb-2 space-x-0 space-y-2 sm:flex-row sm:space-x-4 sm:space-y-0 sm:mb-6"
        >
          <div class="w-full">
            <label for="event_name" class="block mb-2 text-sm font-semibold text-indigo-900">
              Topic
            </label>
            <input
              type="text"
              id="event_name"
              disabled
              class="bg-indigo-50 border border-indigo-300 text-indigo-900 text-sm rounded-lg focus:ring-indigo-500 focus:border-indigo-500 block w-full p-2.5"
              placeholder=""
              :value="announcement?.name"
              required
            />
          </div>
        </div>

        <div
          class="flex flex-col items-center mb-2 space-x-0 space-y-2 sm:flex-row sm:space-x-4 sm:space-y-0 sm:mb-6"
        >
          <div class="w-full">
            <label for="event_detail" class="block mb-2 text-sm font-semibold text-indigo-900">
              Detail
            </label>
            <input
              type="text"
              id="event_detail"
              disabled
              class="bg-indigo-50 border border-indigo-300 text-indigo-900 text-sm rounded-lg focus:ring-indigo-500 focus:border-indigo-500 block w-full p-2.5"
              placeholder=""
              :value="announcement?.description[0]"
              required
            />
          </div>
        </div>
      </div>
    </RouterLink>
  </main>
</template>
