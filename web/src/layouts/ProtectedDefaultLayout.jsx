import React from 'react'
import DefaultLayout from './DefaultLayout'
import ProtectedRoute from '../components/common/protectedRoute/ProtectedRoute'
import { AuthProvider } from '../hooks/useAuth'

export default function ProtectedDefaultLayout() {
    return (
        <AuthProvider>
            <ProtectedRoute>
                <DefaultLayout />
            </ProtectedRoute>
        </AuthProvider>
    );
}
