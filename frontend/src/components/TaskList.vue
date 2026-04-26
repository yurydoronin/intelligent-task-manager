<template>
  <div>
    <TaskItem
        v-for="task in tasks"
        :key="task.id"
        :task="task"
        @delete="$emit('delete', task.id)"
    />
  </div>
</template>

<script setup>
import TaskItem from "./TaskItem.vue";

defineProps({
  tasks: Array
});
</script>