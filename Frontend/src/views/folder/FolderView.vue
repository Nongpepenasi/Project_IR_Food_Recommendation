<template>
    <main class="flex flex-col items-center justify-center">
        <div
            class="mt-5 font-fig flex items-center justify-center p-3 w-3/4 sm:w-2/4 h-4/5 text-2xl font-bold text-gray-900">
            Folder(s)
        </div>

        <!-- <div class="grid lg:grid-cols-3 md:grid-cols-1 gap-24 gap-y-12 mt-10 "> -->
            <FolderCard v-for="folder in folders" :key="folder.id" :folder="folder"></FolderCard>
        <!-- </div> -->

    </main>
</template>
  
<script setup lang="ts">
import { type FolderItem } from '@/type';
import { computed, ref, watchEffect, type Ref, onMounted } from 'vue';
import { useFolderStore } from '@/stores/folder';
import FolderCard from '@/components/FolderCard.vue';

const folders = ref<FolderItem[] | null> (null)


onMounted(async () => {
    try {
        const response = await useFolderStore().getFolder
        folders.value = response
        console.log(response)
    } catch (error) {
        console.log('Error fetching student data:', error)
    }
})

</script>
  