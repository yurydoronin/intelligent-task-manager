<template>
  <div class="window">
    <div class="titlebar">Создание задачи</div>

    <div class="content form">
      <input v-model="form.name" placeholder="Название" />
      <textarea v-model="form.description" placeholder="Описание" />

      <select v-model="form.priority">
        <option>LOW</option>
        <option>MEDIUM</option>
        <option>HIGH</option>
      </select>

      <button @click="submit">Создать</button>
    </div>
  </div>
</template>

<script setup lang="ts">
import { reactive } from 'vue'
import { createTask } from '@/api/taskApi'
import { useRouter } from 'vue-router'

const router = useRouter()

const form = reactive({
  name: '',
  description: '',
  priority: ''
})

async function submit() {
  await createTask(form)
  router.push('/')
}
</script>