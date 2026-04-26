<template>
  <div class="window">
    <div class="titlebar">
      <span>Task Board</span>
      <button @click="$router.push('/create')">Создать</button>
    </div>

    <div class="content">
      <div v-for="task in tasks" :key="task.id" class="card">
        <div class="card-title">{{ task.name }}</div>
        <div class="card-desc">{{ task.description }}</div>

        <div class="actions">
          <button @click="remove(task.id)">Удалить</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { onMounted, ref } from 'vue'
import { getActiveTasks, deleteTask } from '../api/taskApi'

const tasks = ref<any[]>([])

async function load() {
  tasks.value = await getActiveTasks()
}

async function remove(id: string) {
  await deleteTask(id)
  await load()
}

onMounted(load)
</script>