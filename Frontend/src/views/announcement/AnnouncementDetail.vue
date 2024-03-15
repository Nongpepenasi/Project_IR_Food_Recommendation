<script setup lang="ts">
import type { AnnouncementItem, } from '@/type'
import type { PropType } from 'vue'

defineProps({
  announcement: {
    type: Object as PropType<AnnouncementItem>
  }
})


</script>

<template>
  <div v-if="announcement">
    <div class="student-class flex flex-col items-center justify-center">
      <div class="grid gap-5 grid-cols-2 p-3 w-3/4 h-4/5 border border-gray-700
        rounded-lg mb-4 bg-white shadow-md">
        <div class="flex justify-center">
          <img :src="announcement?.images[0]" alt="" class="crop rounded-lg" />
        </div>
        <div class="my-auto">
          <span class="text-base text-gray-500 text-md font-semibold font-fig">Student Detail: </span> <br />
          <span class="font-fig name font-bold text-black"> {{ announcement?.name }}</span><br />
          <span class="text-base text-black text-md font-fig">Description: 
            <p v-for="(desc, index) in announcement.description" :key="index">
              {{ desc }}
            </p></span> <br />
          <span class="text-base text-black text-md font-fig">Ingredient: 
            <p v-for="(ingredient, index) in announcement.recipeIngredientParts" :key="index">
              {{ ingredient }}
            </p></span> <br />
          <span class="text-base text-black text-md font-fig">Instructions: 
            <p v-for="(instructions, index) in announcement.recipeInstructions" :key="index">
              {{ instructions }}
            </p></span> <br />
        </div>
      </div>
    </div>
  </div>

</template>
