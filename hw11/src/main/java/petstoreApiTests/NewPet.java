package petstoreApiTests;

public class NewPet {

        private Integer id;
        private Category category;
        private String name;
        private String[] photoUrls;
        private Tag[] tags;
        private Enum<PetStatus> status;

        public NewPet(Integer id, Category category, String name, String[] photoUrls, Tag[] tags, Enum<PetStatus> status) {
            this.id = id;
            this.category = category;
            this.name = name;
            this.photoUrls = photoUrls;
            this.tags = tags;
            this.status = status;
        }

        public NewPet() {
        }

        public Integer getId() {
            return id;
        }

        public Category getCategory() {
            return category;
        }

        public String getName() {
            return name;
        }

        public String[] getPhotoUrls() {
            return photoUrls;
        }

        public Tag[] getTags() {
            return tags;
        }

        public Enum<PetStatus> getStatus() {
            return status;
        }

        static class Category {
            private Integer id;
            private String name;

            public Category(Integer id, String name) {
                this.id = id;
                this.name = name;
            }

            public Category() {
            }

            public Integer getId() {
                return id;
            }

            public String getName() {
                return name;
            }

            @Override
            public String toString() {
                return "Category{" +
                        "id=" + id +
                        ", name='" + name + '\'' +
                        '}';
            }
        }

        static class Tag {
            private Integer id;
            private String name;

            public Tag(Integer id, String name) {
                this.id = id;
                this.name = name;
            }

            public Tag() {
            }

            public Integer getId() {
                return id;
            }

            public String getName() {
                return name;
            }

            @Override
            public String toString() {
                return "Tag{" +
                        "id=" + id +
                        ", name='" + name + '\'' +
                        '}';
            }
        }

        @Override
        public String toString() {
            return "Pet{" +
                    "id=" + id +
                    ", category=" + category +
                    ", name='" + name + '\'' +
                    ", photoUrls=" + photoUrls +
                    ", tags=" + tags +
                    ", status='" + status + '\'' +
                    '}';
        }
    }

