<template>
    <div class="container mt-5">
        <div class="row">
            <div class="col">
                <button type="button" class="ajoute btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal">
                    Ajouter
                </button>
                <table class="table">
                    <thead>
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">id_composant</th>
			<th scope="col">id_meuble</th>
			<th scope="col">quantite</th>
			 <!-- <th scope="col">Action</th>-->
                            <th scope="col">Action</th>
                            <th>mofifier</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr v-for="(item, index) in Liste" :key="index" @click="selectItem(item)">
                            <th scope="row">{{ index + 1 }}</th>
                             <td>{{ item.idComposant }}</td>
	<td>{{ item.idMeuble }}</td>
	<td>{{ item.quantite }}</td>
	 <!-- <td>{{ item.temperatureF }}</td> -->
                            <td>
                                <button type="button" data-bs-toggle="modal" data-bs-target="#editModal"
                                    class="btn btn-primary btn-sm">Modifier</button>
                            </td>
                            <td>
                                <button class="btn btn-danger btn-sm" data-bs-toggle="modal"
                                    data-bs-target="#deleteModal">Supprimer</button>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

    <div class="container p-5">
        <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title text-danger" id="exampleModalLabel">Ajouter</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <form @submit.prevent="submitForm">
                            
                            <div class="mb-3"><label for="idComposant" class="form-label">id_composant</label><input type="text" class="form-control" id="idComposant" v-model="idComposant"></div>
		<div class="mb-3"><label for="idMeuble" class="form-label">id_meuble</label><input type="text" class="form-control" id="idMeuble" v-model="idMeuble"></div>
		<div class="mb-3"><label for="quantite" class="form-label">quantite</label><input type="text" class="form-control" id="quantite" v-model="quantite"></div>
		
                            <!-- <div class="mb-3">
                                <label for="inputNom" class="form-label">TemperatureF</label>
                                <input type="text" class="form-control" id="inputNom" v-model="TemperatureF">
                            </div> -->

                            <button type="submit" class="btn btn-primary">Ajouter</button>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-warning" data-bs-dismiss="modal">Fermer</button>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="modal fade" id="editModal" tabindex="-1" aria-labelledby="editModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title text-danger" id="editModalLabel">Modifier</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <form @submit.prevent="submitForm">
                        <div class="mb-3"><label for="idComposant" class="form-label">id_composant</label><input type="text" class="form-control" id="idComposant" v-model="selectedItem.idComposant"></div>
		<div class="mb-3"><label for="idMeuble" class="form-label">id_meuble</label><input type="text" class="form-control" id="idMeuble" v-model="selectedItem.idMeuble"></div>
		<div class="mb-3"><label for="quantite" class="form-label">quantite</label><input type="text" class="form-control" id="quantite" v-model="selectedItem.quantite"></div>
		
                        <!-- <div class="mb-3">
                            <label for="inputAge" class="form-label">summary</label>
                            <input type="text" class="form-control" id="inputAge" v-model="selectedItem.summary">
                        </div> -->
                        <button type="submit" class="btn btn-primary" style="width: ;: =100%;">Modifier</button>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-warning" data-bs-dismiss="modal">Fermer</button>
                </div>
            </div>
        </div>
    </div>

    <div class="modal fade" id="deleteModal" tabindex="-1" aria-labelledby="deleteModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title text-danger" id="deleteModalLabel">Supprimer</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <center>
                    <p style="padding: 20px 0px;">etes vous sure de vouloir supprimer cet element ?</p>
                </center>
                <button type="submit" class="btn-choice btn btn-primary">oui</button>
                <button type="button" class="btn-choice btn btn-danger" data-bs-dismiss="modal">non</button>
            </div>
        </div>
    </div>
</template>

<script>
import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap/dist/js/bootstrap.min.js";

export default {
    data() {
        return {
            idComposant,
				idMeuble,
				quantite,
				
            <!-- 
                TemperatureC,
                TemperatureF,
                summary
             -->
            // id_composant: []
			idComposant: [],
			// id_meuble: []
			idMeuble: [],
			
            <!-- Personne[], -->
            Liste: [],
            selectedItem: {},
        };
    },
    mounted() {
        this.findAll();
    },
    methods: {
        async findAll() {
            try {
                const response = await fetch('http://localhost:8080/meublecomposant');
                if (!response.ok) {
                    throw new Error('Erreur de réseau');
                }
                this.Liste = await response.json();
            } catch (error) {
                console.error('Erreur lors de la récupération des personnes:', error);
            }
        },
        async addElement() {
            try {
                const response = await fetch('http://localhost:8080/meublecomposant', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify({
                        idComposant: this.idComposant,
				idMeuble: this.idMeuble,
				quantite: this.quantite,
				
                       <!-- temperatureF: this.TemperatureF,
                        temperatureC: this.TemperatureC,
                        summary: this.summary -->
                    })
                });
                if (!response.ok) {
                    throw new Error('Erreur lors de l\'ajout de l\'élément');
                }
                this.findAll();
            } catch (error) {
                console.error('Erreur lors de l\'ajout de l\'élément:', error);
            }
        },
        async updateElement() {
            try {
                const response = await fetch(`http://localhost:8080/meublecomposant/${this.selectedItem.id}`, {
                    method: 'PUT',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify({
                        idComposant: this.selectedItem.idComposant,
				idMeuble: this.selectedItem.idMeuble,
				quantite: this.selectedItem.quantite,
				
                        <!--
                        temperatureF: this.selectedItem.temperatureF,
                        temperatureC: this.selectedItem.temperatureC,
                        summary: this.selectedItem.summary -->
                    })
                });
                if (!response.ok) {
                    throw new Error('Erreur lors de la modification de l\'élément');
                }
                this.findAll();
            } catch (error) {
                console.error('Erreur lors de la modification de l\'élément:', error);
            }
        },
        async deleteElement(id) {
            try {
                const response = await fetch(`http://localhost:8080/meublecomposant/${id}`, {
                    method: 'DELETE'
                });
                if (!response.ok) {
                    throw new Error('Erreur lors de la suppression de l\'élément');
                }
                this.findAll();
            } catch (error) {
                console.error('Erreur lors de la suppression de l\'élément:', error);
            }
        },
        submitForm() {
            if (this.selectedItem.id) {
                this.updateElement();
            } else {
                this.addElement();
            }
        },
        selectItem(item) {
            this.selectedItem = item;
        },
    }
};
</script>

<style>
.ajoute {
    height: 100px;
    width: 100px;
    margin-bottom: 20px;
}

.btn-choice {
    border-radius: 0px;
}</style>
