<script setup lang="ts">
import type { FolderItem } from '@/type'
import type { PropType } from 'vue'
import { useAuthStore } from '@/stores/auth.ts'

const authStore = useAuthStore()

defineProps({
  folder: {
    type: Object as PropType<FolderItem>
  }
})
</script>

<template>
  <main class="flex flex-col items-center justify-center">
    <RouterLink :to="{ name: 'folder-detail', params: { id: folder?.id } }">
      <div
        class="mt-5 mb-10 font-fig flex flex-col items-left justify-left p-3 w-auto sm:w-auto h-auto text-xl font-bold text-gray-900 bg-white border border-gray-300 rounded-lg shadow-md"
      >
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
              :value="folder?.name"
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
              :value="folder?.ownAnnouncement"
              required
            />
          </div>
        </div>
      </div>
    </RouterLink>
  </main>
</template>
