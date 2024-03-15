<script setup lang="ts">
import {ref} from 'vue'
import { useStudentStore } from '@/stores/student'
import { useTeacherStore } from '@/stores/teacher';
import { storeToRefs } from 'pinia'
import type { AnnouncementItem } from '@/type';
import { useAnnouncementStore } from '@/stores/announcement';

const props = defineProps({
    id: Number
})

const announcement = ref<AnnouncementItem | null>(null)

const announcementStore = useAnnouncementStore()

announcementStore.getAnnouncementById(props.id!)
.then((response) =>{
    announcement.value = response
})

</script>

<template>
    <div v-if="announcement">
        <!-- <div class="text-lg text-center text-black font-sans hover:font-serif flex flex-col p-4">
        </div> -->
        <RouterView :announcement="announcement"></RouterView>

    </div>
</template>