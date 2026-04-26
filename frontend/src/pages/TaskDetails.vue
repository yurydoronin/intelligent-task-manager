<script setup lang="ts">
import { useRoute } from 'vue-router'

const route = useRoute()
const id = route.params.id
</script>

<template>
  <div class="glass">
    <h2>Task ID: {{ id }}</h2>
  </div>
</template>