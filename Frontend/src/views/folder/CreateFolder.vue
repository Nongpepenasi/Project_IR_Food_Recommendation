<template>
    <div id="flashMessage" class="mb-2 animate-pulse text-center text-base font-fig bg-green-500 font-fig text-white"
        v-if="message">
        <h4>{{ message }}</h4>
    </div>
    <FlashMessage />
    <main class="flex flex-col items-center justify-center">
        <div
            class="mt-5 font-fig flex items-center justify-center p-3 w-3/4 sm:w-2/4 h-4/5 text-2xl font-bold text-gray-900">
            Create Folder
        </div>

        <div
            class="mt-2 mb-10 font-fig flex flex-col items-center justify-center p-3 w-3/4 sm:w-2/4 h-auto text-xl font-bold text-gray-900 bg-white border border-gray-300 rounded-lg shadow-md">
            <!-- Announcement Header with Photo, Name, Date, and Time -->
            <div class="flex items-center space-x-3">
                <!-- <img :src="teacher?.images" alt="Person's Photo" class="object-cover w-20 h-20 rounded-full"> -->
                <!-- <div>
                    <p class="font-fig font-semibold text-md">{{teacher?.name}}</p>
                    <p class="font-fig text-sm font-semibold text-gray-600">Date: {{currentDate}}</p>
                    <p class="font-fig text-sm font-semibold text-gray-600">Time: {{currentTime}}</p>
                </div> -->
            </div>

            <div class="items-center mt-4 mb-2 lg:mb-2 lg:mt-2 w-full text-[#202142]">
        <div class="flex flex-col items-center space-x-0 space-y-2 sm:flex-row sm:space-x-4 sm:space-y-0 ">
            <div class="items-center mt-4 lg:mb-2 lg:mt-2 w-full text-[#202142]">
                <div
                    class="flex flex-col items-center mb-2 space-x-0 space-y-2 sm:flex-row sm:space-x-4 sm:space-y-0 sm:mb-6">
                    <div class="w-full">
                        <label for="event_name" class="block mb-2 text-sm font-semibold text-indigo-900">
                            Folder Name</label>
                        <input type="text" id="event_name"
                            class="bg-indigo-50 border border-indigo-300 text-indigo-900 text-sm rounded-lg focus:ring-indigo-500 focus:border-indigo-500 block w-full p-2.5 "
                            placeholder="Write topic here..." :value="folderName" @input="folderName = $event.target.value" required>
                    </div>
                </div>

                <div
                    class="flex flex-col items-center mb-2 space-x-0 space-y-2 sm:flex-row sm:space-x-4 sm:space-y-0 sm:mb-6">
                        </div>

                                <div class="w-100"></div>
                                <div class="col-12 mt-3 text-center">
                                  
                                </div>
                            </div>
                        </div>

                        <div class="flex justify-center">

                            <!-- Post button -->
                            <button @click="showConfirmation"
                                class="flex text-white bg-emerald-500 hover:bg-emerald-700 focus:ring-4 focus:outline-none focus:ring-emerald-300 font-medium rounded-lg text-sm w-full sm:w-auto px-5 py-2 text-center justify-center items-center mt-5">
                                Create
                            </button>


                        </div>


                    </div>
                </div>

    </main>
</template>

<script setup lang="ts">
import { useMessageStore } from '@/stores/message'
import { useAuthStore } from '@/stores/auth'
import { storeToRefs } from 'pinia'
import { useTeacherStore } from '@/stores/teacher'
import { useFolderStore } from '@/stores/folder'
import { type FolderItem } from '@/type'
import { type TeacherItem } from '@/type'

const storeMessage = useMessageStore()
const authStore = useAuthStore()
const { message } = storeToRefs(storeMessage)
const folderName = ref("");
const eventDetail = ref("");
const teacher = ref<TeacherItem | null>(null)
// import { ref } from 'vue';

const currentTime = ref('');
const currentDate = ref('');



// Function to update the currentTime and currentDate
const updateDateTime = () => {
  const now = new Date();
  const hours = now.getHours();
  const minutes = now.getMinutes();
  const formattedTime = `${hours}:${minutes < 10 ? '0' : ''}${minutes}`;
  currentTime.value = formattedTime;

  const options = { year: 'numeric', month: 'long', day: 'numeric' };
  currentDate.value = now.toLocaleDateString(undefined, options);
};

const showConfirmation = () => {
    if (!folderName.value) {
        alert('Please fill in all required fields (Topic and Detail) before posting.');
    } else if (confirm("Are you sure you want to create this folder?")) {
        onSubmit();
    }
};

const onSubmit = () => {
    // Handle the post data submission here
    // You can send your post data to the server or perform any necessary actions
    // This method is called when the user confirms the post in the dialog

    useFolderStore().addFolder(folderName.value)
    // authStore.announcementPost(eventName.value, eventDetail.value,mediaURL.value)
    // location.reload()
    // Display a success message
    storeMessage.updateMessage('Folder is create!');
    reset(); 
    setTimeout(() => {
        storeMessage.resetMessage();
        location.reload()
    
    }, 4000);
};

// onMounted(async () => {
//     try {
//         const response = await useTeacherStore().getTeacher()
//         teacher.value = response
//         updateDateTime();
//         setInterval(updateDateTime, 60000);
//         if (teacher.value) {
//             console.log(teacher.value)
//         }
//     } catch (error) {
//         console.log('Error fetching student data:', error)
//     }
// })

import { ref, onMounted } from 'vue';

const reset = () => {
    folderName.value = "";
};
</script>