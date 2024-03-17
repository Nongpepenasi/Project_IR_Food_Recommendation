<script setup lang="ts">
import {ref} from 'vue'
import type { FolderItem } from '@/type';
import { useFolderStore } from '@/stores/folder';

const props = defineProps({
    id: Number
})

const folder = ref<FolderItem | null>(null)

const folderStore = useFolderStore()

folderStore.getFolderById(props.id!)
.then((response) =>{
    folder.value = response
})

</script>

<template>
    <div v-if="folder">
        <!-- <div class="text-lg text-center text-black font-sans hover:font-serif flex flex-col p-4">
        </div> -->
        <RouterView :folder="folder"></RouterView>

    </div>
</template>