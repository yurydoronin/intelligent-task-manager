<template>
  <div class="container">
    <h1>Task Manager</h1>

    <TaskForm @created="loadTasks" />

    <TaskList
      :tasks="tasks"
      @delete="onDelete"
    />

    <ConfirmDialog
      v-if="showConfirm"
      @confirm="confirmDelete"
      @cancel="showConfirm = false"
    />
  </div>
</template>

<script setup>
import { ref, onMounted } from "vue";
import { getActiveTasks, deleteTask } from "@/api/taskApi";

import TaskForm from "../components/TaskForm.vue";
import TaskList from "../components/TaskList.vue";
import ConfirmDialog from "../components/ConfirmDialog.vue";

const tasks = ref([]);
const showConfirm = ref(false);
const taskToDelete = ref(null);

const loadTasks = async () => {
  const res = await getActiveTasks();
  tasks.value = res.data;
};

const onDelete = (id) => {
  taskToDelete.value = id;
  showConfirm.value = true;
};

const confirmDelete = async () => {
  await deleteTask(taskToDelete.value);
  showConfirm.value = false;
  loadTasks();
};

onMounted(loadTasks);
</script>