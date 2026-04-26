<template>
  <div class="task">
    <div>
      <b>{{ task.name }}</b> ({{ task.priority }})
    </div>

    <div>{{ task.description }}</div>

    <div>Status: {{ task.status }}</div>

    <button @click="$emit('delete')">Delete</button>
  </div>
</template>

<script setup>
defineProps({
  task: Object
});
</script>