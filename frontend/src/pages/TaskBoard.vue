<script setup lang="ts">
import {onMounted, ref} from 'vue'
import {deleteTask, getTaskById} from '@/api/taskApi'
import type {Task} from '@/types/task'

const tasks = ref<Task[]>([])

const load = async () => {
  tasks.value = await getTaskById()
}

const remove = async (id: string) => {
  await deleteTask(id)
  await load()
}

onMounted(load)
</script>

<template>
  <div class="app">
    <aside class="sidebar">
      <h2>Tasks</h2>
      <div class="menu-item">All</div>
      <div class="menu-item">Active</div>
    </aside>

    <main class="main">
      <div class="topbar">
        <span>Task Manager</span>
      </div>

      <div class="content">
        <div class="create">
          <input placeholder="Name"/>
          <button>Create</button>
        </div>

        <table class="table">
          <thead>
          <tr>
            <th>Name</th>
            <th>Priority</th>
            <th>Status</th>
            <th></th>
          </tr>
          </thead>

          <tbody>
          <tr v-for="t in tasks" :key="t.id">
            <td>{{ t.name }}</td>
            <td>{{ t.priority }}</td>
            <td>{{ t.status }}</td>
            <td>
              <button @click="remove(t.id)">Delete</button>
            </td>
          </tr>
          </tbody>
        </table>
      </div>
    </main>
  </div>
</template>