<script setup lang="ts">
import type { AnnouncementItem } from '@/type'
import type { PropType } from 'vue'
import { useAuthStore } from '@/stores/auth.ts'

const authStore = useAuthStore()

defineProps({
  announcement: {
    type: Object as PropType<AnnouncementItem>
  }
})
</script>

<template>
  <main class="flex flex-col items-center justify-center">
    <RouterLink :to="{ name: 'announcement-detail', params: { id: announcement?.recipeId } }">
      <div
      class="w-80 gap-5 grid-cols-2 p-3 h-full border border-gray-700
        rounded-lg mb-4 bg-white shadow-md"
      >
      
        <div class="flex space-x-3">
          <div class=" mx-auto">
            <img
            :src="announcement?.images"
            alt="Person's Photo"
            class="object-cover w-36 h-36 rounded-full"
          />
          </div>

        </div>
        <div
          class="flex flex-col items-center mb-2 space-x-0 space-y-2 sm:flex-row sm:space-x-4 sm:space-y-0 sm:mb-6"
        >
          <div class="w-full">
            <label for="event_name" class="block mb-2 text-sm font-semibold text-indigo-900">
              Name
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
              :value="announcement?.description"
              required
            />
          </div>
        </div>

      </div>
    </RouterLink>
  </main>
</template>
