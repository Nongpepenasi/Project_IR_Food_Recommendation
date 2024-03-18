<script setup lang="ts">
import type { AnnouncementItem, } from '@/type'
import type { PropType } from 'vue'
import { useRouter, RouterLink } from 'vue-router'

const props = defineProps({
  announcement: {
    type: Object as PropType<AnnouncementItem>
  }
})

const router = useRouter()

const onSubmit = () => {
  router.push({name: 'recipes-view', params: {id: 0}});
  
};

</script>

<template>
  <div v-if="announcement">
    <div class="student-class flex flex-col items-center justify-center">
      <div class="grid gap-5 grid-cols-2 p-3 w-3/4 h-4/5 border border-gray-700
        rounded-lg mb-4 bg-white shadow-md">
        <div class="flex justify-center">
          <img :src="announcement?.images" alt="" class="crop rounded-lg" />
        </div>
        <div class="my-auto">
          <span class="text-base text-gray-500 text-md font-semibold font-fig">Dish: </span> <br />
          <span class="font-fig name font-bold text-black"> {{ announcement?.name }}</span><br />
          <span class="text-base text-black text-md font-fig"><b>Description:</b> {{ announcement.description }}</span> <br /><br />
          <span class="text-base text-black text-md font-fig"><b>Ingredient:</b> {{ announcement.recipeIngredientParts }}</span> <br /><br />
          <!-- <span class="text-base text-black text-md font-fig"><b>Quantity:</b> {{ announcement.recipeIngredientQuantities }}</span> <br /> -->
          <span class="text-base text-black text-md font-fig"><b>Instructions:</b> {{ announcement.recipeInstructions }}</span>
          
        </div>
        <button @click="showConfirmation"
                                class="flex text-white bg-emerald-500 hover:bg-emerald-700 focus:ring-4 focus:outline-none focus:ring-emerald-300 font-medium rounded-lg text-sm w-full sm:w-auto px-5 py-2 text-center justify-center items-center mt-5">
                                Add to
                              </button>
      </div>
    </div>
  </div>

</template>
