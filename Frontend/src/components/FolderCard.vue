<script setup lang="ts">
import type { FolderItem } from '@/type'
import type { PropType } from 'vue'
import { useAuthStore } from '@/stores/auth.ts'
import {  onMounted, ref } from 'vue';
import { useFolderStore } from '@/stores/folder';
import { useMessageStore } from '@/stores/message'
import { useRouter, RouterLink } from 'vue-router'
import { storeToRefs } from 'pinia'

const authStore = useAuthStore()

const storeMessage = useMessageStore()
const { message } = storeToRefs(storeMessage)

const props = defineProps({
  folder: {
    type: Object as PropType<FolderItem>
  }
})

const recipes = ref()

const showConfirmation = () => {
  if (confirm("Are you sure you want to delete this folder?")) {
        onSubmit();
    }
};

const onSubmit = () => {

if (props.folder != null) {
  useFolderStore().deleteFolder(props.folder?.id)
  storeMessage.updateMessage('Folder is delete!');
  location.reload()
  
}

};

onMounted(async () => {
    try {
        recipes.value = props.folder?.ownAnnouncement
        console.log(recipes.value)
    } catch (error) {
        console.log( error)
    }
})
</script>

<template>
  <main class="flex flex-col items-center justify-center">
    <!-- <RouterLink :to="{ name: 'folder-detail', params: { id: folder?.id } }"> -->
      <div
        class="w-96 gap-5 grid-cols-2 p-3 h-4/5 border border-gray-700
        rounded-lg mb-4 bg-white shadow-md"
      >
        <!-- <div
          class="flex flex-col items-center mb-2 space-x-0 space-y-2 sm:flex-row sm:space-x-4 sm:space-y-0 sm:mb-6"
        > -->
          <div class="w-full text-center">
            <label for="event_name" class="center block mb-2 text-xl font-semibold text-indigo-900">
               {{ folder?.name }}
            </label>
            <label for="event_detail" class="block mb-2 text-l font-semibold text-indigo-900">
              Menu lists
            </label>
            <div v-for="recipe in recipes" :key="recipe.id" :recipe="recipe">
              <span class="text-sm text-black font-fig">{{ recipe?.name }}</span> <br/>
            </div>
            <button @click="showConfirmation"
                                class="flex text-white bg-red-500 hover:bg-red-700 focus:ring-4 focus:outline-none focus:ring-red-300 font-medium rounded-lg text-sm mx-auto block px-5 py-2 justify-center items-center mt-5">
                                Delete
                              </button>
          </div>
        
      </div>
    <!-- </RouterLink> -->
  </main>
</template>
