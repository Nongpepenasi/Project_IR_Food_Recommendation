<script setup lang="ts">
import { useFolderStore } from '@/stores/folder';
import type { FolderItem, AnnouncementItem} from '@/type'
import type { PropType } from 'vue'
import { useMessageStore } from '@/stores/message'
import { useRouter, RouterLink } from 'vue-router'
import { storeToRefs } from 'pinia'
import {  onMounted, ref } from 'vue';

const router = useRouter()

const storeMessage = useMessageStore()
const { message } = storeToRefs(storeMessage)

const props = defineProps({
  folder: {
    type: Object as PropType<FolderItem>
  }
})


const showConfirmation = () => {
  if (confirm("Are you sure you want to delete this folder?")) {
        onSubmit();
    }
};

const onSubmit = () => {

  if (props.folder != null) {
    useFolderStore().deleteFolder(props.folder?.id)
    storeMessage.updateMessage('Folder is delete!');
    router.push({name: "folder-view"})
    location.reload()
    
  }
  
};

const recipes = ref()

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
  <div id="flashMessage" class="mb-2 animate-pulse text-center text-base font-fig bg-green-500 font-fig text-white"
        v-if="message">
        <h4>{{ message }}</h4>
    </div>
    <FlashMessage />
  <div v-if="folder">
    <div class="student-class flex flex-col items-center justify-center">
      <div class="grid gap-5 grid-cols-2 p-3 w-3/4 h-4/5 border border-gray-700
        rounded-lg mb-4 bg-white shadow-md">
        <div class="my-auto">
          <span class="text-base text-gray-500 text-md font-semibold font-fig">Menu Name: </span> <br />
          <div v-for="recipe in recipes" :key="recipe.id" :recipe="recipe">
            <span class="text-base text-black text-md font-fig">{{ recipe?.name }}</span> <br/>
          </div>
          <!-- {{ folder.ownAnnouncement }}
          <div>
            <img :src="recipe?.images" alt="" class="crop rounded-lg" />
            <span class="font-fig name font-bold text-black"> {{ recipe?.name }}</span><br />
            <span class="text-base text-black text-md font-fig">Description: {{ recipe.description }}</span> <br />
          </div> -->
          <button @click="showConfirmation"
                                class="flex text-white bg-red-500 hover:bg-red-700 focus:ring-4 focus:outline-none focus:ring-red-300 font-medium rounded-lg text-sm w-full sm:w-auto px-5 py-2 text-center justify-center items-center mt-5">
                                Delete
                              </button>
        </div>
      </div>
    </div>
  </div>

</template>
