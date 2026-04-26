import { createRouter, createWebHistory } from 'vue-router'
import TaskBoardView from '@/pages/TaskBoard.vue'
import TaskDetailsView from '@/pages/TaskDetails.vue'

export default createRouter({
    history: createWebHistory(),
    routes: [
        { path: '/', component: TaskBoardView },
        { path: '/task/:id', component: TaskDetailsView }
    ]
})