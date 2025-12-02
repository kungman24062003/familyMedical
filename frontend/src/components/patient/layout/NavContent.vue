<!-- src/components/layout/NavContent.vue -->
<template>
  <div class="flex flex-col h-full">
    <!-- Logo -->
    <div class="p-6 border-b">
      <router-link to="/" class="flex items-center gap-2">
        <div
          class="w-8 h-8 rounded-lg bg-primary flex items-center justify-center text-primary-foreground font-bold text-xl"
        >
          M
        </div>
        <span class="text-xl font-bold text-foreground">MedFamily</span>
      </router-link>
    </div>

    <!-- Navigation -->
    <div class="flex-1 py-6 px-4 space-y-1">
      <router-link
        v-for="item in navItems"
        :key="item.href"
        :to="item.href"
        class="flex items-center gap-3 px-4 py-3 rounded-lg transition-all duration-200 group"
        :class="route.path === item.href
          ? 'bg-primary text-primary-foreground shadow-md shadow-primary/20'
          : 'text-muted-foreground hover:bg-secondary hover:text-foreground'"
      >
        <component :is="item.icon" class="w-5 h-5" />
        <span class="font-medium">{{ item.title }}</span>
      </router-link>
    </div>

    <!-- User info -->
    <div class="p-4 border-t bg-secondary/30">
      <div class="flex items-center gap-3 px-2 mb-4">
        <Avatar>
          <AvatarImage :src="currentUser.avatar" />
          <AvatarFallback>VN</AvatarFallback>
        </Avatar>
        <div class="flex-1 min-w-0">
          <p class="text-sm font-medium text-foreground truncate">{{ currentUser.name }}</p>
          <p class="text-xs text-muted-foreground truncate">{{ currentUser.email }}</p>
        </div>
      </div>
      <Button
        variant="outline"
        class="w-full justify-start gap-2 text-muted-foreground hover:text-destructive hover:bg-destructive/10 hover:border-destructive/50"
      >
        <LogOut class="w-4 h-4" /> Sign Out
      </Button>
    </div>
  </div>
</template>

<script setup lang="ts">
import { useRoute } from 'vue-router'
import { LayoutDashboard, FileText, Users, Stethoscope, MessageSquare, LogOut } from 'lucide-vue-next'
import Avatar from '../ui/avatar.vue'
import AvatarImage from '../ui/avatarImage.vue'
import AvatarFallback from '../ui/avatarFallback.vue'
import Button from '../ui/button.vue'
import { currentUser } from '@/lib/mockData'

const route = useRoute()

const navItems = [
  { title: 'Dashboard', href: '/', icon: LayoutDashboard },
  { title: 'My Records', href: '/profile', icon: FileText },
  { title: 'Family Members', href: '/family', icon: Users },
  { title: 'Find Doctor', href: '/doctors', icon: Stethoscope },
  { title: 'Feedback', href: '/feedback', icon: MessageSquare },
]
</script>
