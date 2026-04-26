<template>
  <form class="form" @submit.prevent="submit">
    <input v-model="form.name" placeholder="Name" required />

    <textarea v-model="form.description" placeholder="Description"></textarea>

    <select v-model="form.priority">
      <option value="LOW">LOW</option>
      <option value="MEDIUM">MEDIUM</option>
      <option value="HIGH">HIGH</option>
    </select>

    <input type="datetime-local" v-model="form.deadline" />

    <button type="submit">Create</button>
  </form>
</template>

<script setup>
import { reactive } from "vue";
import { createTask } from "@/api/taskApi";

const emit = defineEmits(["created"]);

const form = reactive({
  name: "",
  description: "",
  priority: "",
  deadline: null
});

const submit = async () => {
  await createTask({
    ...form,
    deadline: form.deadline ? new Date(form.deadline).toISOString() : null
  });

  form.name = "";
  form.description = "";
  form.priority = "";
  form.deadline = null;

  emit("created");
};
</script>

<style scoped>
.form {
  display: grid;
  grid-template-columns: 1fr;
  gap: 10px;
  max-width: 420px;
}

input, textarea, select {
  width: 100%;
  padding: 8px;
  box-sizing: border-box;
}

button {
  padding: 8px;
  cursor: pointer;
}
</style>