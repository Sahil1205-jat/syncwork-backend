#!/bin/bash

# Build the backend application for Render
echo "Building SyncWork Backend..."
cd SyncWork-backend
./mvnw clean package -DskipTests
cd ..
echo "Build complete!"
