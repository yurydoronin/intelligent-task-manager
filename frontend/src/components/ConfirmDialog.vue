<template>
  <div class="dialog">
    <p>Удалить задачу?</p>

    <button @click="$emit('confirm')">Да</button>
    <button @click="$emit('cancel')">Нет</button>
  </div>
</template>